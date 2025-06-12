import api from '@/api/api'

export interface TourData {
    type: string
    description: string
    price: number
}

export default {

    getTours() {
        return api.get('/tours')
    },

    createTour(tourData: TourData) {
        return api.post('/tours', tourData)
    },

    getTourById(id: number) {
        return api.get(`/tours/${id}`)
    },

    updateTour(id: number, updatedData: Partial<TourData>) {
        return api.put(`/tours/${id}`, updatedData)
    },
}