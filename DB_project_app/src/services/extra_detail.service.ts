import api from '@/api/api'

export interface ExtraDetailData {
  participants: number
  price: number
  extra: { id: number }
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
