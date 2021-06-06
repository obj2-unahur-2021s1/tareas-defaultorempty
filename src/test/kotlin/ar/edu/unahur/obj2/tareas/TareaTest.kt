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

    var tareaSimple = TareaSimple(100,responsable, listaDeEmpleadosTareaSimple, 50000)

    it("dar nomina de empleados"){
      tareaSimple.darNominaDeEmpleados().count().shouldBe(3)
    }

    it("calcular cantidad de horas necesarias para finalizar la tarea"){
      tareaSimple.horasNecesariasParaFinalizarTarea().shouldBe(50)
    }

    it("obtener el costo de una tarea"){

      //50000+15000+15000+50000
      tareaSimple.costoDeUnaTarea().shouldBe(130000)
    }
  }
})
