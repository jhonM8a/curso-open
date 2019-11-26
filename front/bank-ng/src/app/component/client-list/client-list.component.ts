import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/domain/cliente';
import { ClienteService } from 'src/app/service/cliente.service';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  public listaCliente: Cliente[];

  constructor(public clienteService:ClienteService) { }

  ngOnInit() {
    this.findAll();
  }

  findAll(){
    this.clienteService.findAll().subscribe(data =>{
      this.listaCliente = data
    });
  }
}
