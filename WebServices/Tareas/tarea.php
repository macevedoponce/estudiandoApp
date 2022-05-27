<?php

include_once 'db.php';

class Tarea extends DB{
    
    function obtenerTareas(){
        $query = $this->connect()->query('SELECT * FROM tareas ');
        return $query;
    }
    function obtenerCursos(){
        $query = $this->connect()->query('SELECT * FROM cursos ');
        return $query;
    }

}

?>