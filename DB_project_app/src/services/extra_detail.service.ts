import api from '@/api/api'

export interface ExtraDetailData {
  cantPersona: number
  precioTotal: number
  idExtra: number
}

export default {
  getExtraDetails() {
    return api.get('/extra_details')
  },

  createExtraDetail(data: ExtraDetailData) {
    return api.post('/extra_details', data)
  },

  updateExtraDetail(id: number, updatedData: Partial<ExtraDetailData>) {
    return api.put(`/extra_details/${id}`, updatedData)
  },

  getExtraDetailById(id: number) {
    return api.get(`/extra_details/${id}`)
  },
}
