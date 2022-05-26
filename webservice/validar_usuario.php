
<?php

include 'conexion.php';

//if(!empty($_POST['usuario']) && !empty($_POST['password'])){
    $docDni=$_POST['usuario'];
    $docPassword=$_POST['password'];
    $sentencia=$conexion->prepare("SELECT * FROM docentes WHERE docDni=?");
    $sentencia->bind_param('s',$docDni);
    $sentencia->execute();
    $resultado = $sentencia->get_result();
    $fila = $resultado->fetch_assoc();
    if($fila&& password_verify($docPassword,$fila['docPassword'])){
       echo json_encode($fila,JSON_UNESCAPED_UNICODE);
      }

    $sentencia->close();
    $conexion->close();
//} 


?>

<?php
/*
include 'conexion.php';
$docDni=$_POST['usuario'];
$docPassword=$_POST['password'];

//$usu_usuario="angeloroncal@developeru.net";
//$usu_password="12345678";

$sentencia=$conexion->prepare("SELECT * FROM docentes WHERE docDni=? AND docPassword=?");
$sentencia->bind_param('ss',$docDni,$docPassword);
$sentencia->execute();
$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()) {
         echo json_encode($fila,JSON_UNESCAPED_UNICODE);     
}
else{
    echo "0";
}
$sentencia->close();
$conexion->close();
*/
?>


<?php
/*
include 'conexion.php';
$docDni = $_POST['usuario'];
$docPassword = $_POST['password'];

$sentencia = $conexion->prepare("SELECT * FROM docentes WHERE docDni=? AND docPassword=?");
$sentencia->bind_param('ss',$docDni,$docPassword);
$sentencia->execute();

$resultado = $sentencia->get_result();
if($fila = $resultado->fetch_assoc()){
    echo json_encode($fila, JSON_UNESCAPED_UNICODE);
}
$sentencia->close();
$conexion->close();
*/
?>
