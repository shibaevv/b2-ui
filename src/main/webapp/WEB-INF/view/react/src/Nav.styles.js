import { makeStyles } from '@material-ui/core/styles'

export const useStyles = makeStyles(() => ({
  nav: {
    '& ul': {
      display: 'flex',
      flexDirection: 'column',
      height: 'auto',
      listStyle: 'none',
      margin: 0,
      padding: 0,
      width: '68px',
    },
    '& a, button': {
      alignItems: 'center',
      background: 'unset',
      border: 'none',
      borderLeft: '3px solid #3D4548',
      cursor: 'pointer',
      display: 'flex',
      height: '48px',
      justifyContent: 'center',
      outline: 'none',
      width: 'calc(100% - 3px)',
    },
    '& a.active': {
      backgroundColor: '#111414',
      borderBottom: 'none',
      borderLeft: '3px solid #547517',
    },
    '& a:hover, button:hover': {
      backgroundColor: '#111414',
      borderBottom: 'none',
      borderLeft: '3px solid #547517',
    },
    backgroundColor: '#3D4548',
    margin: 0,
    width: '68px',
  },
}))
