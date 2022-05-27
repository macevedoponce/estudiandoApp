<?php
include 'conexion.php';

$id=$_POST['id'];
$titulo=$_POST['titulo'];
$descripcion=$_POST['descripcion'];
$retroalimentacion=$_POST['retroalimentacion'];

$consulta = "UPDATE tareas set titulo = '$titulo', descripcion = '$descripcion',  retroalimentacion = '$retroalimentacion' WHERE id = '$id' ";
mysqli_query($conexion,$consulta) or die (mysqli_error($conexion));
mysqli_close($conexion);

?>