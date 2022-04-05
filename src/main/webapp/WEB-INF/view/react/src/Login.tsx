import { useEffect, useRef, useState } from 'react'
import Button from '@material-ui/core/Button'
import CircularProgress from '@material-ui/core/CircularProgress'
import { AzureSignIn } from './AzureSignIn'
import PcLogoStackedHorizontal from './assets/PC-Stacked-Horizontal.png'
import TextField from '@material-ui/core/TextField'
import useMediaQuery from '@material-ui/core/useMediaQuery'
import { useStyles } from './Login.styles'

export const Login: React.FC = () => {
  const classes = useStyles()
  const [error, setError] = useState<boolean | string | JSX.Element>(false)
  const [isLoading, setLoading] = useState<boolean>(false)
  const loginForm = useRef<HTMLFormElement>(null)

  const [email, setEmail] = useState<string>('')
  const [password, setPassword] = useState<string>('')

  const isSmall = useMediaQuery('(max-width:768px)')

  // eslint-disable-next-line compat/compat
  const queryString = new URLSearchParams(window.location.search).toString()
  const loginError = queryString.includes('error=true')

  useEffect(() => {
    if (loginError) {
      setError('Login failed')
    }
  }, [loginError])

  const validate = () => {
    let errorMsg: string | JSX.Element = ''
    const validateEmailRegex = /\S+@\S+\.\S+/
    if (!validateEmailRegex.test(email)) {
      errorMsg = 'Please enter a valid email address.'
    }
    if (password.length === 0) {
      const passErr = 'Please enter your password.'
      errorMsg = errorMsg ? (
        <span>
          {errorMsg} <br />
          {passErr}
        </span>
      ) : (
        passErr
      )
    }
    setError(errorMsg)

    return !errorMsg
  }

  const login = () => {
    const valid = validate()

    const formData = new FormData()
    formData.append('username', email)
    formData.append('password', password)

    if (valid) {
      setLoading(true)
      if (loginForm?.current) {
        loginForm.current.submit()
      }
    }
  }

  const handleKeypress = (e: React.KeyboardEvent) => {
    if (e.key === 'Enter') {
      login()
    }
  }

  const emailUpdate = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value)
  }

  const passwordUpdate = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value)
  }

  return (
    <div className={classes.login}>
      <main>
        <header>
          <img src={PcLogoStackedHorizontal} alt="Book a Banker part of the Westpac Group" />
        </header>
        {isLoading && !error && <CircularProgress className="loading" />}
        {error && <p className="error">{error}</p>}
        <form action="/perform_login" method="post" ref={loginForm}>
          <h1>Welcome to Book a Banker</h1>
          <p>
            Already a customer or partner? <a href="#index">Request access</a>
          </p>
          <p>
            New to Book a Banker? <a href="#index">Learn more</a>
          </p>
          <AzureSignIn />
          <TextField
            id="email"
            name="username"
            color="secondary"
            label="Email"
            onChange={emailUpdate}
            onKeyPress={handleKeypress}
            variant="filled"
            fullWidth
            size="small"
          />
          <TextField
            id="password"
            name="password"
            color="secondary"
            label="Password"
            onChange={passwordUpdate}
            onKeyPress={handleKeypress}
            variant="filled"
            fullWidth
            size="small"
            type="password"
          />
          <Button variant="contained" color="primary" fullWidth size="large" onClick={login} type="button">
            Sign In
          </Button>
          <p className="trouble">
            Having trouble? Contact us on {isSmall ? <a href="tel:1800 774 6279">1800 774 6279</a> : '1800 774 6279'}
          </p>
        </form>
      </main>
    </div>
  )
}
