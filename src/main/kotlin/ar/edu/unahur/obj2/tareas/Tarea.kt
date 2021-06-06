package ar.edu.unahur.obj2.tareas

interface ITarea {
    fun horasNecesariasParaFinalizarTarea(): Int
    fun costoDeUnaTarea(): Double
    fun calcularSalarioTotalDeEmpleados(): Double
    fun darNominaDeEmpleados(): MutableList<Empleado>
}

class TareaSimple(
    val cantidadHorasEstimadas: Int,
    val responsable: Empleado,
    var empleados: MutableList<Empleado>,
    val costoInfraestructura: Int
) : ITarea {
    override fun horasNecesariasParaFinalizarTarea() = cantidadHorasEstimadas / empleados.size

    override fun costoDeUnaTarea(): Double {
        return costoInfraestructura + this.calcularSalarioTotalDeEmpleados() + responsable.calcularCuantoCobraPorCantidadDeHorasTrabajadas(
            cantidadHorasEstimadas
        )
    }

    override fun calcularSalarioTotalDeEmpleados(): Double {
        return empleados.sumByDouble { it.calcularCuantoCobraPorCantidadDeHorasTrabajadas(this.horasNecesariasParaFinalizarTarea()) }
    }

    override fun darNominaDeEmpleados(): MutableList<Empleado> {
        var nominaEmpleados = empleados
        nominaEmpleados.add(responsable)
        return nominaEmpleados
    }

}

class TareaIntegracion(val responsable: Empleado) : ITarea {

    var listaDeTareas = mutableListOf<ITarea>()

    override fun horasNecesariasParaFinalizarTarea(): Int {
        return listaDeTareas.sumBy { it.horasNecesariasParaFinalizarTarea() } + (listaDeTareas.sumBy { it.horasNecesariasParaFinalizarTarea() } / 8)
    }

    override fun costoDeUnaTarea() = costoSubTareas() + bonus()

    fun costoSubTareas() = listaDeTareas.sumByDouble { it.costoDeUnaTarea() }
    fun bonus() = costoSubTareas() * 0.03

    override fun calcularSalarioTotalDeEmpleados(): Double {
        return listaDeTareas.sumByDouble { it.calcularSalarioTotalDeEmpleados() }
    }

    override fun darNominaDeEmpleados(): MutableList<Empleado> {
        var listaCompleta = listaDeTareas.flatMap { it.darNominaDeEmpleados() }.toMutableList()
        listaCompleta.add(responsable)
        return listaCompleta
    }

}

class Empleado(var cobraPorHora: Double) {
    fun saberCuantoCobraPorHora(): Double = cobraPorHora
    fun calcularCuantoCobraPorCantidadDeHorasTrabajadas(cantidadDeHoras: Int): Double {
        return cantidadDeHoras * saberCuantoCobraPorHora()
    }
}

