import api from '@/api/api'

export interface ReservationData {
    fecha: string
    hora: string
    descripcion: string
    subtotalViaje?: number
    subtotalExtra?: number
    total?: number
    idUsuario: number
}

export default {
    
    getReservations() {
        return api.get('/reservas')
    },

    createReservation( reservationData: ReservationData) {
        return api.post('/reservas', reservationData)
    },

    updateReservation(id: number, updatedData: Partial<ReservationData>) {
        return api.put(`/reservas/${id}`, updatedData)
    },

    getReservtaionById(id: number) {
        return api.get(`/reservas/${id}`)
    },
}