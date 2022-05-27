<?php

include_once 'tarea.php';

class ApiTareas{

    function getAllTareas(){
        $tarea = new Tarea();
        $tareas = array();
        $tareas["datos"] = array();

        $res = $tarea->obtenerTareas();

        if($res->rowCount()){
            while ($row = $res->fetch(PDO::FETCH_ASSOC)){
    
                $tarea=array(
                    "id" => $row['id'],
                    "titulo" => $row['titulo'],
                    "descripcion" => $row['descripcion'],
                    "retroalimentacion" => $row['retroalimentacion'],
                );
                array_push($tareas["datos"], $tarea);
            }
            echo json_encode($tareas,JSON_UNESCAPED_UNICODE);
        }else{
            echo json_encode(array('mensaje' => 'No hay elementos'));
        }
    }
    function getAllCursos(){
        $tarea = new Tarea();
        $tareas = array();
        $tareas["datos"] = array();

        $res = $tarea->obtenerCursos();

        if($res->rowCount()){
            while ($row = $res->fetch(PDO::FETCH_ASSOC)){
    
                $tarea=array(
                    "id" => $row['id'],
                    "codigo" => $row['curCodigo'],
                    "nombre" => $row['curNombre'],
                    "docente" => $row['docId'],
                    "grado" => $row['graId'],
                    "seccion" => $row['secId'],
                );
                array_push($tareas["datos"], $tarea);
            }
            echo json_encode($tareas,JSON_UNESCAPED_UNICODE);
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