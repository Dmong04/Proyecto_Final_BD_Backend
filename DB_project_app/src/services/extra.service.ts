import api from '@/api/api'

export interface ExtraData {
    name: string
    description: string
    price: number
}

export default {

    getExtras() {
        return api.get('/extra')
    },

    createExtra(extraData: ExtraData) {
        return api.post('/extra', extraData)
    },

    getExtraById(id: number) {
        return api.get(`/extra/${id}`)
    },

    updateExtra(id: number, updatedData: Partial<ExtraData>) {
        return api.put(`/extra/${id}`, updatedData)
    },
}
