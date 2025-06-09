import api from '@/api/api'

export interface PassengerData {
  nombre: string
  edad: number
  idDetalleViaje: number
}

export default {
  getPassengers() {
    return api.get('/pasajeros')
  },

  createPassenger(data: PassengerData) {
    return api.post('/pasajeros', data)
  },

  updatePassenger(id: number, updatedData: Partial<PassengerData>) {
    return api.put(`/pasajeros/${id}`, updatedData)
  },

  getPassengerById(id: number) {
    return api.get(`/pasajeros/${id}`)
  },
}