package ar.edu.unahur.obj2.tareas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class TareaTest : DescribeSpec({
    describe("Una tarea simple") {

        var responsable = Empleado(500.0)
        var empleadoUno = Empleado(300.0)
        var empleadoDos = Empleado(300.0)

        var listaDeEmpleadosTareaSimple = mutableListOf<Empleado>()
        listaDeEmpleadosTareaSimple.add(empleadoUno)
        listaDeEmpleadosTareaSimple.add(empleadoDos)

        var tareaSimple = TareaSimple(100, responsable, listaDeEmpleadosTareaSimple, 50000)

        it("dar nomina de empleados") {
            tareaSimple.darNominaDeEmpleados().count().shouldBe(3)
        }

        it("calcular cantidad de horas necesarias para finalizar la tarea") {
            tareaSimple.horasNecesariasParaFinalizarTarea().shouldBe(50)
        }

        it("obtener el costo de una tarea") {
            tareaSimple.costoDeUnaTarea().shouldBe(130000)
        }
    }

    describe("Una tarea Integracion con una tarea simple") {

        var responsable = Empleado(600.0)
        var responsableIntegracion = Empleado(800.0)
        var empleadoUno = Empleado(250.0)
        var empleadoDos = Empleado(400.0)

        var listaDeEmpleadosTareaSimple = mutableListOf<Empleado>()
        listaDeEmpleadosTareaSimple.add(empleadoUno)
        listaDeEmpleadosTareaSimple.add(empleadoDos)

        var tareaSimple = TareaSimple(32, responsable, listaDeEmpleadosTareaSimple, 70000)
        var tareaIntegracion = TareaIntegracion(responsableIntegracion)
        tareaIntegracion.listaDeTareas.add(tareaSimple)

        it("dar nomina de empleados") {
            tareaIntegracion.darNominaDeEmpleados().count().shouldBe(4)
        }

        it("calcular cantidad de horas necesarias para finalizar la tarea") {
            tareaIntegracion.horasNecesariasParaFinalizarTarea().shouldBe(18)
        }

        it("obtener el costo de una tarea") {
            tareaIntegracion.costoDeUnaTarea().shouldBe(102588)
        }
    }

})
