import './App.css'
import { ThemeProvider, createMuiTheme } from '@material-ui/core/styles'
import { useEffect, useState } from 'react'
import { Dashboard } from './Dashboard'
import { Login } from './Login'
import axios from 'axios'

const theme = createMuiTheme({
  palette: {
    primary: {
      main: '#86BC25',
    },
    secondary: {
      main: '#90a4ae',
    },
  },
})

export const App: React.FC = () => {
  const [authenticated, setAuthenticated] = useState(false)

  useEffect(() => {
    axios
      .get('/user')
      .then(() => {
        setAuthenticated(true)
      })
      .catch(() => {
        setAuthenticated(false)
      })
  }, [])

  return (
    <ThemeProvider theme={theme}>
      {!authenticated && <Login />}
      {authenticated && <Dashboard />}
    </ThemeProvider>
  )
}
