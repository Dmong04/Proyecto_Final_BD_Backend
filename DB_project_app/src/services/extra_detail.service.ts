import api from '@/api/api'

export interface ExtraDetailData {
  cantPersona: number
  precioTotal: number
  idExtra: number
}

export default {
  getExtraDetails() {
    return api.get('/detalle-extras')
  },

  createExtraDetail(data: ExtraDetailData) {
    return api.post('/detalle-extras', data)
  },

  updateExtraDetail(id: number, updatedData: Partial<ExtraDetailData>) {
    return api.put(`/detalle-extras/${id}`, updatedData)
  },

  getExtraDetailById(id: number) {
    return api.get(`/detalle-extras/${id}`)
  },
}
