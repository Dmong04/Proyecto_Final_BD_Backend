import { ref, onMounted } from 'vue'
import extraService from '@/services/extra.service'
import type { Extra } from '@/models/extra'

export function ExtraComponet() {
    const extras = ref<Extra[]>([])
    const loading = ref(false)
    const error = ref(null)

    const loadExtras = async () => {
        loading.value = true
        error.value = null

        try {
            const response = await extraService.getExtras()
            extras.value = response.data
        } catch (err: any) {
            console.error('Error al cargar extras: ', err)
            error.value = err
        } finally {
            loading.value = false
        }
    }

    const deleteExtraById = async (id: number) => {
        try {
          await extraService.deleteExtra(id)
          extras.value = extras.value.filter(extra => extra.id !== id)
        } catch (err: any) {
          console.error('Error al eliminar el extra:', err)
          error.value = err
        }
      }

    onMounted(() => {
        loadExtras()
    })

    return {
        extras,
        loading,
        error,
        reload: loadExtras,
    }

}