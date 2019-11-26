import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/domain/cliente';
import { Activo } from 'src/app/domain/activo';
import { ClienteService } from 'src/app/service/cliente.service';
import { ActivoService } from 'src/app/service/activo.service';

@Component({
  selector: 'app-cliente-save',
  templateUrl: './cliente-save.component.html',
  styleUrls: ['./cliente-save.component.css']
})
export class ClienteSaveComponent implements OnInit {

  public cliente: Cliente;
  public listaActivo: Activo[];

  public showMsg:boolean=false;
  public msg:string

  constructor(public clienteService: ClienteService, public activoService: ActivoService) { }

  ngOnInit() {
    this.findAllActivo();
    this.cliente = new Cliente(0, '','','','','',1)
  }

  public findAllActivo(){
    this.listaActivo = this.activoService.findAll();
  }
  public save(){
    this.clienteService.save(this.cliente).subscribe(data=>{
      this.showMsg = true;
      this.msg= 'Cliente saved'
    }, error=>{
      debugger
      this.showMsg=true;
      Object.values(error.error).map(x =>{this.msg = x+this.msg})
      

    });
  }
}
