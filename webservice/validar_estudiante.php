<?php
include 'conexion.php';

$aluDni=$_POST['usuario'];
$aluPassword=$_POST['password'];

$queryAlumno = mysqli_query($conexion,"select * from alumnos where aluDni = '$aluDni'");
$NumFilas = mysqli_num_rows($queryAlumno);
$buscarPass = mysqli_fetch_array($queryAlumno);

if (($NumFilas == 1)&&(password_verify($aluPassword,$buscarPass['aluPassword']))){

    echo json_encode($fila,JSON_UNESCAPED_UNICODE);
}
$conexion->close();
?>