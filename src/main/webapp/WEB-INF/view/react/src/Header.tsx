import BallotIcon from '@material-ui/icons/Ballot'
import { useStyles } from './Header.styles'

export const Header: React.FC = () => {
  const classes = useStyles()

  return (
    <header className={classes.header}>
      <a href="#index" className={classes.home}>
        Home
      </a>
      <BallotIcon color="primary" />
      <h1>Orders</h1>
      <ul className={classes.nav}>
        <li>
          <a href="#index">Loads</a>
        </li>
        <li>
          <a href="#index" className="active">
            Shipments
          </a>
        </li>
        <li>
          <a href="#index">Purchase Orders</a>
        </li>
      </ul>
    </header>
  )
}
