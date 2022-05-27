<?php

include 'conexion.php';
$titulo=$_POST['titulo'];
$descripcion=$_POST['descripcion'];
$retroalimentacion=$_POST['retroalimentacion'];

$consulta = "insert into tareas values('','".$titulo."','".$descripcion."','".$retroalimentacion."')";
mysqli_query($conexion,$consulta) or die (mysqli_error($conexion));
mysqli_close($conexion);

?>