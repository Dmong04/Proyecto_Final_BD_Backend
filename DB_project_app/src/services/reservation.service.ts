import api from '@/api/api'

export interface ReservationData {
  date: string
  time: string
  description: string
  tourPrice: number
  extraPrice: number
  total: number  
  detailExtra: { id: number | null }
  detailTour: { id: number }
  user: { id: number }
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
