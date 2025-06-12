import { ref, onMounted } from 'vue'
import reservationService from '@/services/reservation.service'
import type { Reservation } from '@/models/reservation'
import type { Passenger } from '@/models/passenger'
import passengerService from '@/services/passenger.service'
import type { TourDetail } from '@/models/tour_detail'
import tour_detailService from '@/services/tour_detail.service'
import type { ExtraDetail } from '@/models/extra_detail'
import extra_detailService from '@/services/extra_detail.service'
import type { Provider } from '@/models/provider'
import providerService from '@/services/provider.service'

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

export function TourDetailComponent(){
  const tour_details = ref<TourDetail[]>([])
  const loading = ref(false)
  const error = ref(null)


  const loadTourDetails = async () => {
    loading.value = true
    error.value = null

    try {
      const response = await tour_detailService.getTripDetails()
      tour_details.value = response.data
      console.log("Detalles del tour: ", tour_details.value)
    } catch (err: any) {
      console.error('Error al cargar detalles del tour: ', err)
      error.value = err
    } finally {
      loading.value = false
    }
  }

  onMounted(() => {
    loadTourDetails()
  })

  return {
    tour_details,
    loading,
    error,
    reload: loadTourDetails
  }    
}

export function PassengerComponent(){
  const passengers = ref<Passenger[]>([])
  const loading = ref(false)
  const error = ref(null)


  const loadPassengers = async () => {
    loading.value = true
    error.value = null

    try {
      const response = await passengerService.getPassengers()
      passengers.value = response.data
      console.log("Pasajeros: ", passengers.value)
    } catch (err: any) {
      console.error('Error al cargar pasajeros: ', err)
      error.value = err
    } finally {
      loading.value = false
    }
  }

  onMounted(() => {
    loadPassengers()
  })

  return {
    passengers,
    loading,
    error,
    reload: loadPassengers
  }  
}

export function ExtraDetailComponent(){
  const extra_details = ref<ExtraDetail[]>([])
  const loading = ref(false)
  const error = ref(null)


  const loadExtraDetails = async () => {
    loading.value = true
    error.value = null

    try {
      const response = await extra_detailService.getExtraDetails()
      extra_details.value = response.data
      console.log("Detalles extra: ", extra_details.value)
    } catch (err: any) {
      console.error('Error al cargar detalles extra: ', err)
      error.value = err
    } finally {
      loading.value = false
    }
  }

  onMounted(() => {
    loadExtraDetails()
  })

  return {
    extra_details,
    loading,
    error,
    reload: loadExtraDetails
  }    
}

export function ProviderComponent(){
  const providers = ref<Provider[]>([])
  const loading = ref(false)
  const error = ref(null)


  const loadProviders = async () => {
    loading.value = true
    error.value = null

    try {
      const response = await providerService.getProviders()
      providers.value = response.data
      console.log("Detalles extra: ", providers.value)
    } catch (err: any) {
      console.error('Error al cargar detalles extra: ', err)
      error.value = err
    } finally {
      loading.value = false
    }
  }

  onMounted(() => {
    loadProviders()
  })

  return {
    providers,
    loading,
    error,
    reload: loadProviders
  }    
}