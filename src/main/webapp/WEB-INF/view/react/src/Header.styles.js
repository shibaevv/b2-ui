import Logo from './assets/pc-logo-icon.png'
import { makeStyles } from '@material-ui/core/styles'

export const useStyles = makeStyles(() => ({
  header: {
    '& h1': {
      fontFamily: 'Montserrat-Bold',
      fontSize: '1.3rem',
      margin: '0 0 0 1rem',
      textTransform: 'uppercase',
    },
    alignItems: 'center',
    background: '#f3f1ee',
    borderBottom: '1px solid #E0E0E0',
    display: 'flex',
    height: '68px',
    padding: 0,
  },
  home: {
    backgroundColor: '#3D4548',
    backgroundImage: `url(${Logo})`,
    backgroundRepeat: 'no-repeat',
    backgroundPosition: 'center center',
    borderBottom: '1px solid #111414',
    display: 'flex',
    flexShrink: 0,
    height: '68px',
    marginRight: '1rem',
    textIndent: '-9999px',
    width: '68px',
  },
  nav: {
    display: 'flex',
    height: '100%',
    listStyle: 'none',
    margin: 0,
    '& li': {
      marginRight: '1rem',
    },
    '& a': {
      alignItems: 'center',
      borderTop: '2px solid #f3f1ee',
      color: '#1E2324',
      display: 'flex',
      fontFamily: 'Montserrat-Bold',
      fontSize: '0.9rem',
      height: 'calc(100% - 4px)',
      textDecoration: 'none',
      textTransform: 'uppercase',
    },
    '& a.active, a:hover': {
      borderBottom: '3px solid #547517',
      color: '#547517',
      textDecoration: 'none',
    },
  },
}))
