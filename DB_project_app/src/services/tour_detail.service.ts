import api from '@/api/api'

export interface TripDetailData {
  numPasajeros: number
  origen: string
  destino: string
  idViaje: number
  idProveedor: number
}

export default {
  getTripDetails() {
    return api.get('/tour_details')
  },

  createTripDetail(data: TripDetailData) {
    return api.post('/tour_details', data)
  },

  updateTripDetail(id: number, updatedData: Partial<TripDetailData>) {
    return api.put(`/tour_details/${id}`, updatedData)
  },

  getTripDetailById(id: number) {
    return api.get(`/tour_details/${id}`)
  },
}