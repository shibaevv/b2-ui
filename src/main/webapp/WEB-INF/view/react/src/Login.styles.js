import GoRicky from './assets/riki-watene.png'
import { makeStyles } from '@material-ui/core/styles'

export const useStyles = makeStyles(() => ({
  login: {
    backgroundImage: `url(${GoRicky})`,
    backgroundRepeat: 'no-repeat',
    backgroundSize: 'auto 100%',
    color: '#263238',
    display: 'flex',
    fontFamily: 'HelveticaNeue',
    height: '100vh',
    justifyContent: 'flex-end',
    width: '100%',
    '& main': {
      background: 'white',
      textAlign: 'center',
      width: '488px',
    },
    '& header': {
      alignItems: 'center',
      background: '#f3f1ee',
      display: 'flex',
      height: '80px',
      paddingLeft: '24px',
    },
    '& form': {
      margin: '8rem 3rem 0',
      textAlign: 'left',
    },
    '& h1': {
      fontFamily: 'Montserrat-Bold',
      fontSize: '1.2rem',
      margin: '0 0 2rem',
      textTransform: 'uppercase',
    },
    '& a': {
      color: '#86BC25',
      textDecoration: 'none',
    },
    '& a:hover': {
      textDecoration: 'underline',
    },
    '& button span': {
      color: 'white',
      fontFamily: 'Montserrat-Bold',
      textTransform: 'uppercase',
    },
    '& .MuiFormControl-root.MuiTextField-root': {
      marginBottom: '1.5rem',
    },
    '& p.error': {
      border: '1px solid #d32f2f',
      borderRadius: '2px',
      color: '#d32f2f',
      marginLeft: '3rem',
      marginTop: '2rem',
      padding: '1rem',
      position: 'absolute',
      textAlign: 'left',
      width: '358px',
    },
    '& .loading': {
      marginLeft: '-20px',
      marginTop: '2.8rem',
      position: 'absolute',
    },
    '& p.trouble': {
      color: '#777',
      fontSize: '0.8rem',
    },
    '& p + p': {
      marginBottom: '2.2rem',
    },
    '@media screen and (max-width: 768px)': {
      '& main': {
        width: '100%',
      },
      '& p.error': {
        width: 'calc(100% - 8rem)',
      },
    },
    '@media screen and (max-width: 1024px)': {
      backgroundPosition: '70% 100%',
    },
  },
}))
