import { ref, onMounted } from 'vue'
import tourService from '@/services/tour.service'
import type { Tour } from '@/models/tour'

export function TourComponent() {
    const tours = ref<Tour[]>([])
    const loading = ref(false)
    const error = ref(null)

    const loadTours = async () => {
        loading.value = true
        error.value = null

        try {
            const response = await tourService.getTours()
            tours.value = response.data

        } catch (err: any) {
            console.error('Error al cargar tours: ', err)
            error.value = err
        } finally {
            loading.value = false
        }
    }

    onMounted(() => {
        loadTours()
    })

    return {
        tours,
        loading,
        error,
        reload: loadTours
    }
}
