<?php

include 'conexion.php';
$id=$_POST['id'];
$consulta = "DELETE FROM tareas WHERE id='$id' ";
mysqli_query($conexion,$consulta) or die (mysqli_error($conexion));
mysqli_close($conexion);

?>