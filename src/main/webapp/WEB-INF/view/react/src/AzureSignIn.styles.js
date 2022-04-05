import { makeStyles } from '@material-ui/core/styles'

export const useStyles = makeStyles(() => ({
  azureButton: {
    '& span.MuiButton-label span': {
      color: '#3c4146',
      fontFamily: 'HelveticaNeue',
      marginLeft: '0.5rem',
      textTransform: 'none',
    },
    border: '1px solid #d6d9dc',
    marginBottom: '1.5rem',
  },
  azureLink: {
    '& span': {
      color: '#3c4146',
      fontFamily: 'HelveticaNeue',
      fontSize: '0.9rem',
      marginLeft: '0.5rem',
      textTransform: 'none',
    },
    '&:hover': {
      backgroundColor: 'rgba(0, 0, 0, 0.04)',
      textDecoration: 'none !important',
    },
    border: '1px solid #d6d9dc',
    borderRadius: '4px',
    display: 'flex',
    justifyContent: 'center',
    marginBottom: '1.5rem',
    padding: '16px',
    width: 'calc(100% - 34px)',
  },
}))
