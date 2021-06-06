package ar.edu.unahur.obj2.tareas

interface ITarea {
    fun horasNecesariasParaFinalizarTarea(): Int
    fun costoDeUnaTarea(): Double
    fun calcularSalarioTotalDeEmpleados(): Double
    fun darNominaDeEmpleados(): MutableList<Empleado>
}

class TareaSimple(
    var cantidadHorasEstimadas: Int,
    var responsable: Empleado,
    var empleados: MutableList<Empleado>,
    var costoInfraestructura: Int
) : ITarea {
    override fun horasNecesariasParaFinalizarTarea(): Int {
        return cantidadHorasEstimadas / empleados.size
    }

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

class Empleado(var cobraPorHora: Double) {
    fun saberCuantoCobraPorHora(): Double = cobraPorHora
    fun calcularCuantoCobraPorCantidadDeHorasTrabajadas(cantidadDeHoras: Int): Double {
        return cantidadDeHoras * saberCuantoCobraPorHora()
    }
}

