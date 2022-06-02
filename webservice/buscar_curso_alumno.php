<?php

include 'conexion.php';
$aluDNI = $_GET['aluDNI'];

$consulta = "select cursos.curNombre,curCodigo from alumnos,matriculas,grados,cursos 
where alumnos.id = matriculas.aluId and matriculas.graId = grados.id and grados.id = cursos.graId and alumnos.aluDni = '$aluDNI' ";
$resultado = $conexion -> query($consulta);

while($fila = $resultado -> fetch_array()){
    $alumnos[] = array_map('utf8_encode',$fila);
}

echo json_encode($alumnos);
$resultado -> close();

?>