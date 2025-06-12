import api from '@/api/api'

export interface ExtraData {
    name: string
    description: string
    pricePerPerson: number
}

export default {

    getExtras() {
        return api.get('/extras')
    },

    createExtra(extraData: ExtraData) {
        return api.post('/extras', extraData)
    },

    getExtraById(id: number) {
        return api.get(`/extras/${id}`)
    },

    updateExtra(id: number, updatedData: Partial<ExtraData>) {
        return api.put(`/extras/${id}`, updatedData)
    },
}
