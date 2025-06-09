import api from '@/api/api'

export interface ProviderData {
  nombre: string
  descripcion: string
  correo: string
  telefonos?: string[]
}

export default {
  getProviders() {
    return api.get('/proveedores')
  },

  createProvider(providerData: ProviderData) {
    return api.post('/proveedores', providerData)
  },

  updateProvider(id: number, updatedData: Partial<ProviderData>) {
    return api.put(`/proveedores/${id}`, updatedData)
  },

  getProviderById(id: number) {
    return api.get(`/proveedores/${id}`)
  },
}