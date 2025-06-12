import api from '@/api/api'

export interface ReservationData {
  fecha: string
  hora: string
  descripcion: string
  subtotalViaje?: number
  subtotalExtra?: number
  idDetalleExtra?: number | null
  idDetalleViaje: number
  total?: number
  idUsuario: number
}

export default {
  getReservations() {
    return api.get('/reservation')
  },

  createReservation(reservationData: ReservationData) {
    return api.post('/reservation', reservationData)
  },

  updateReservation(id: number, updatedData: Partial<ReservationData>) {
    return api.put(`/reservation/${id}`, updatedData)
  },

  getReservtaionById(id: number) {
    return api.get(`/reservation/${id}`)
  },
}
