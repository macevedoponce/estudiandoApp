<?php

include_once 'alarma.php';

class ApiAlarmas{

    function getAll(){
        $alarma = new Alarma();
        $alarmas = array();
        $alarmas["datos"] = array();

        $res = $alarma->obtenerTareas();

        if($res->rowCount()){
            while ($row = $res->fetch(PDO::FETCH_ASSOC)){
    
                $alarma=array(
                    "id" => $row['id'],
                    "codigo" => $row['curCodigo'],
                    "nombre" => $row['curNombre'],
                    "docente" => $row['docId'],
                    "grado" => $row['graId'],
                    "seccion" => $row['secId'],
                );
                array_push($alarmas["datos"], $alarma);
            }
            echo json_encode($alarmas,JSON_UNESCAPED_UNICODE);
            //$this->printJSON($alarmas);
        }else{
            echo json_encode(array('mensaje' => 'No hay elementos'));
        }
    }

    function getById($id){
        $alarma = new Alarma();
        $alarmas = array();
        $alarmas["alarma"] = array();

        $res = $alarma->obtenerAlarma($id);

        if($res->rowCount() == 1){
            $row = $res->fetch();
            $alarma=array(
                "id" => $row['alarmaID'],
                "nombre_alarma" => $row['alarmaNombre'],
                "nombre_musica" => $row['alarmaAudio'],
                "hora" => $row['alarmaHora'],
                "minuto" => $row['alarmaMinuto'],
                "segundo" => $row['alarmaSegundo'],
                "usuarioid" => $row['usuarioID'],
            );
            array_push($alarmas["alarma"], $alarma);
            $this->printJSON($alarmas);
        }else{
            echo json_encode(array('mensaje' => 'No hay elementos'));
        }
    }

    function getByusuarioID($usuarioID){
        $alarma = new Alarma();
        $alarmas = array();
        $alarmas["alarma"] = array();

        $res = $alarma->obtenerAlarmaUsuario($usuarioID);

        if($res->rowCount()){
            while ($row = $res->fetch(PDO::FETCH_ASSOC)){
    
                $alarma=array(
                    "id" => $row['alarmaID'],
                    "nombre_alarma" => $row['alarmaNombre'],
                    "nombre_musica" => $row['alarmaAudio'],
                    "hora" => $row['alarmaHora'],
                   # "minuto" => $row['alarmaMinuto'],
                    #"segundo" => $row['alarmaSegundo'],
                    "usuarioid" => $row['usuarioID'],
                );
                array_push($alarmas["alarma"], $alarma);
            }
        
            $this->printJSON($alarmas);
        }else{
            echo json_encode(array('mensaje' => 'No hay elementos'));
        }
    }

    
    function error($mensaje){
        echo '<code>' . json_encode(array('mensaje' => $mensaje)) . '</code>'; 
    }

    function exito($mensaje){
        echo '<code>' . json_encode(array('mensaje' => $mensaje)) . '</code>'; 
    }

    function printJSON($array){
        echo json_encode($array);
    }
}

?>