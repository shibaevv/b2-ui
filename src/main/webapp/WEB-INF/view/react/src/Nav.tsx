import BallotIcon from '@material-ui/icons/Ballot'
import ExitToAppIcon from '@material-ui/icons/ExitToApp'
import GroupIcon from '@material-ui/icons/Group'
import UnarchiveIcon from '@material-ui/icons/Unarchive'
import { useStyles } from './Nav.styles'

export const Nav: React.FC = () => {
  const classes = useStyles()

  return (
    <nav className={classes.nav}>
      <ul>
        <li>
          <a href="#index">
            <GroupIcon color="primary" />
          </a>
        </li>
        <li>
          <a href="#index" className="active">
            <BallotIcon color="primary" />
          </a>
        </li>
        <li>
          <a href="#index">
            <UnarchiveIcon color="primary" />
          </a>
        </li>
        <li>
          <a href="/perform_logout" title="logout">
            <ExitToAppIcon color="primary" />
          </a>
        </li>
      </ul>
    </nav>
  )
}
