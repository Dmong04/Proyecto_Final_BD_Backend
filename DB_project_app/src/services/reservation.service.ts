import api from '@/api/api'

export interface ReservationData {
    date: string
    time: string
    description: string
    tourSubtotal?: number
    extraSubtotal?: number
    extraDetail?: number | null
    tourDetail: number
    total?: number
    user: number
}

export default {
    getReservations() {
        return api.get('/reservas')
    },

    createReservation(reservationData: ReservationData) {
        return api.post('/reservas', reservationData)
    },

    updateReservation(id: number, updatedData: Partial<ReservationData>) {
        return api.put(`/reservas/${id}`, updatedData)
    },

    getReservtaionById(id: number) {
        return api.get(`/reservas/${id}`)
    },
}