package ar.edu.unahur.obj2.tareas

interface ITarea {
    fun horasNecesariasParaFinalizarTarea(): Int
    fun costoDeUnaTarea(): Double
    fun calcularSalarioTotalDeEmpleados(): Double
    fun darNominaDeEmpleados() : MutableList<Empleado>
}

class Empleado(var cobraPorHora : Double){
    fun saberCuantoCobraPorHora(): Double = cobraPorHora
    fun calcularCuantoCobraPorCantidadDeHorasTrabajadas( cantidadDeHoras : Int): Double {
        return cantidadDeHoras * saberCuantoCobraPorHora()
    }
}