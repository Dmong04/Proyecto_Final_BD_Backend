import api from '@/api/api'

export interface ProviderData {
  name: string
  description: string
  email: string
}

export default {
  getProviders() {
    return api.get('/provider')
  },

  createProvider(providerData: ProviderData) {
    return api.post('/provider', providerData)
  },

  updateProvider(id: number, updatedData: Partial<ProviderData>) {
    return api.put(`/provider/${id}`, updatedData)
  },

  getProviderById(id: number) {
    return api.get(`/provider/${id}`)
  },
}