import { ref, onMounted } from 'vue'
import reservationService from '@/services/reservation.service'
import type { Reservation } from '@/models/reservation'

export function ReservationComponent() {

  const reservations = ref<Reservation[]>([])
  const loading = ref(false)
  const error = ref(null)


  const loadTours = async () => {
    loading.value = true
    error.value = null

    try {
      const response = await reservationService.getReservations()
      reservations.value = response.data
      console.log("Reservas: ", reservations.value)
    } catch (err: any) {
      console.error('Error al cargar reservas: ', err)
      error.value = err
    } finally {
      loading.value = false
    }
  }

  onMounted(() => {
    loadTours()
  })

  return {
    reservations,
    loading,
    error,
    reload: loadTours
  }
}
