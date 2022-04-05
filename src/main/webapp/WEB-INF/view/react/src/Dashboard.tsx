import { useEffect, useState } from 'react'
import { DataGrid } from '@material-ui/data-grid'
import { Header } from './Header'
import { Nav } from './Nav'
import axios from 'axios'
import { useStyles } from './Dashboard.styles'

export const Dashboard: React.FC = () => {
  const classes = useStyles()
  const [rowData, setRowData] = useState([])

  useEffect(() => {
    axios.post('/shipment/find', { id: 'abc' }).then((response) => {
      setRowData(response.data)
    })
  }, [])

  const columns = [
    { field: 'id', headerName: 'Shipment ID', width: 70 },
    { field: 'customer', headerName: 'Customer', width: 130 },
    { field: 'from', headerName: 'From', width: 130 },
    { field: 'to', headerName: 'To', width: 130 },
    { field: 'carrier', headerName: 'Carrier', width: 130 },
    { field: 'mode', headerName: 'Mode', width: 130 },
    { field: 'status', headerName: 'Status', width: 130 },
    { field: 'ready', headerName: 'Ready Date', width: 130 },
    { field: 'eta', headerName: 'ETA / Delivered', width: 130 },
  ]

  return (
    <div>
      <Header />
      <div className={classes.content}>
        <Nav />
        <main className={classes.grid} style={{ width: '100%', height: '400px' }}>
          <DataGrid rows={rowData} columns={columns} pageSize={5} checkboxSelection />
        </main>
      </div>
    </div>
  )
}
