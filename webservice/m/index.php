<?php
    include_once 'apialarmas.php';

    $api = new ApiAlarmas();

    if(isset($_GET['id'])){
        $id = $_GET['id'];

        if(is_numeric($id)){
            $api->getById($id);
        }else{
            $api->error('El id es incorrecto');
        }
    }
    else{
        if(isset($_GET['usuarioID'])){
            $usuarioID = $_GET['usuarioID'];
    
            if(is_numeric($usuarioID)){
                $api->getByusuarioID($usuarioID);
            }else{
                $api->error('El usuarioID es incorrecto');
            }
        }else{
            $api->getAll();
        }
        
    }
    
?>