import api from '@/api/api'

export interface ExtraData {
    nombre: string
    descripcion: string
    precioPersona: number
}

export default {

    getExtras() {
        return api.get('/extras')
    },

    createExtra(extraData: ExtraData) {
        return api.post('/tours', extraData)
    },

    getExtraById(id: number) {
        return api.get(`/extras/${id}`)
    },

    updateExtra(id: number, updatedData: Partial<ExtraData>) {
        return api.put(`/extras/${id}`, updatedData)
    },
}
