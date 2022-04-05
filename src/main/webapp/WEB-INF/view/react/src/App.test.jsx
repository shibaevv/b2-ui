import { act, cleanup, fireEvent, render, screen } from '@testing-library/react'
import { App } from './App'
import { Dashboard } from './Dashboard'
import { GoogleSignIn } from './GoogleSignIn'
import { Header } from './Header'
import { Login } from './Login'
import { Nav } from './Nav'
import axios from 'axios'

test('Should render the Header', () => {
  render(<Header />)
  const headerText = screen.getByText(/Home/i)
  expect(headerText).toBeInTheDocument()
})

test('Should render the Nav', () => {
  render(<Nav />)
  const navText = screen.getByTitle(/logout/i)
  expect(navText).toBeInTheDocument()
})

test('Should render the Google signin', () => {
  render(<GoogleSignIn />)
  const googleText = screen.getByText(/Sign in with Google/i)
  expect(googleText).toBeInTheDocument()
})

afterEach(() => {
  cleanup()
})

describe('ensure authentication state works', () => {
  beforeEach(() => {
    jest.mock('axios')
    axios.get = jest.fn()
  })

  afterEach(() => {
    jest.clearAllMocks()
  })

  test('mock /user log in success to see if it loads dashboard', async () => {
    let wrapper
    // mock axios promise
    await act(async () => {
      // eslint-disable-next-line compat/compat
      await axios.get.mockImplementationOnce(() => Promise.resolve({ status: 200 }))
      wrapper = render(<App />)
    })

    await expect(axios.get).toHaveBeenCalledWith('/user')
    await expect(axios.get).toHaveBeenCalledTimes(1)
    const dashBoardText = wrapper.getByText(/Home/i)
    await expect(dashBoardText).toBeInTheDocument()
  })

  test('mock /user log in FAIL to see it keeps login screen', async () => {
    let wrapper
    // mock axios promise
    await act(async () => {
      // eslint-disable-next-line
      await axios.get.mockImplementationOnce(() => Promise.reject({ status: 401 }))
      wrapper = render(<App />)
    })

    await expect(axios.get).toHaveBeenCalledWith('/user')
    await expect(axios.get).toHaveBeenCalledTimes(1)
    const dashBoardText = wrapper.getByText(/Welcome to Book a Banker/i)
    await expect(dashBoardText).toBeInTheDocument()
  })
})

describe('logging in', () => {
  test('no password error', () => {
    const utils = render(<Login />)
    const input = utils.getByLabelText('Email')

    fireEvent.change(input, { target: { value: 'sean@test.com' } })
    fireEvent.click(utils.getByText('Sign In'))

    const errorText = screen.getByText(/Please enter your password./i)
    expect(errorText).toBeInTheDocument()
  })

  test('no email error', () => {
    const utils = render(<Login />)
    const input = utils.getByLabelText('Password')

    fireEvent.change(input, { target: { value: '' } })
    fireEvent.click(utils.getByText('Sign In'))

    const errorText = screen.getByText(/Please enter a valid email address./i)
    expect(errorText).toBeInTheDocument()
  })

  test('bad email error', () => {
    const utils = render(<Login />)
    const input = utils.getByLabelText('Password')

    fireEvent.change(input, { target: { value: 'sean' } })
    fireEvent.click(utils.getByText('Sign In'))

    const errorText = screen.getByText(/Please enter a valid email address./i)
    expect(errorText).toBeInTheDocument()
  })

  test('login server error', () => {
    delete window.location
    window.location = {
      search: '?error=true',
    }
    render(<Login />)
    const errorText = screen.getByText(/Login failed/i)
    expect(errorText).toBeInTheDocument()
  })

  test('submits login form', () => {
    window.HTMLFormElement.prototype.submit = () => null

    const utils = render(<Login />)
    const emailInput = utils.getByLabelText('Email')
    const passwordInput = utils.getByLabelText('Password')

    fireEvent.change(emailInput, { target: { value: 'sean@email.com' } })
    fireEvent.change(passwordInput, { target: { value: 'nicePassword' } })
    fireEvent.click(utils.getByText('Sign In'))

    const errorText = screen.queryByText(/Please enter a valid email address./i)
    expect(errorText).not.toBeInTheDocument()

    const pwErrorText = screen.queryByText(/Please enter your password./i)
    expect(pwErrorText).not.toBeInTheDocument()
  })
})

describe('dashboard', () => {
  beforeEach(() => {
    jest.mock('axios')
    axios.post = jest.fn()
  })

  afterEach(() => {
    jest.clearAllMocks()
  })

  test('mock /shipment/find to see if it tries to load shipments', async () => {
    // mock axios promise
    await act(async () => {
      // eslint-disable-next-line compat/compat
      await axios.post.mockImplementationOnce(() => Promise.resolve({ data: [] }))
      render(<Dashboard />)
    })

    await expect(axios.post).toHaveBeenCalledWith('/shipment/find', { id: 'abc' })
    await expect(axios.post).toHaveBeenCalledTimes(1)
  })
})
