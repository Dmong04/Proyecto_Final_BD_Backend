import api from '@/api/api'

export interface TripDetailData {
  passengerCount: number
  origin: string
  destination: string
  tour: number
  provider: number
}

export default {
  getTripDetails() {
    return api.get('/detalle-viajes')
  },

  createTripDetail(data: TripDetailData) {
    return api.post('/detalle-viajes', data)
  },

  updateTripDetail(id: number, updatedData: Partial<TripDetailData>) {
    return api.put(`/detalle-viajes/${id}`, updatedData)
  },

  getTripDetailById(id: number) {
    return api.get(`/detalle-viajes/${id}`)
  },
}