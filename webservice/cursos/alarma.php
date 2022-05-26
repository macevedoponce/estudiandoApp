<?php

include_once 'db.php';

class Alarma extends DB{
    
    function obtenerTareas(){
        $query = $this->connect()->query('SELECT * FROM cursos ');
        return $query;
    }

    function obtenerTarea($id){
        $query = $this->connect()->prepare('SELECT * FROM alarmas WHERE alarmaID = :id');
        $query->execute(['id' => $id]);
        return $query;
    }

    function obtenerAlarmaUsuario($usuarioID){
        $query = $this->connect()->prepare('SELECT * FROM alarmas WHERE usuarioID = :usuarioID');
        $query->execute(['usuarioID' => $usuarioID]);
        return $query;
    }
}

?>