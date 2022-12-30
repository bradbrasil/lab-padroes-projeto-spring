package design.patterns.service.impl;

import design.patterns.model.Cliente;
import design.patterns.model.ClienteRepository;
import design.patterns.model.Endereco;
import design.patterns.model.EnderecoRepository;
import design.patterns.service.ClienteService;
import design.patterns.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 */

@Service
public class ClienteServiceImpl implements ClienteService {

    // TODO Singleton: injetar os componentes do Spring com @Autowired.

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    // TODO Strategy: Implementar os métodos definidos na Interface.
    // TODO Facade: Abstrair interações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Cliente> buscarTodos(){
        //FIXME Buscar todos os clientes.
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        //FIXME Buscar Cliente por ID
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        SalvarClienteComCep(cliente);
    }

    ;

   @Override
    public void atualizar(Long id, Cliente cliente) {
        //FIXME Buscar Cliente por ID, caso exista:
       Optional<Cliente> clienteBd = clienteRepository.findById(id);
       if (clienteBd.isPresent()) {

           //FIXME Verificar se o endereço do Cliente já existe (pelo CEP).
           //FIXME Caso não exista, integrar com o ViaCEP e persistir o retorno.
           //FIXME Alterar Cliente, vinculando o Endereço (novo ou existente).

           SalvarClienteComCep(cliente);
       }
   }

   @Override
   public void deletar(Long id) {
        //FIXME Deletar cliente por ID.
        clienteRepository.deleteById(id);
    }


    private void SalvarClienteComCep(Cliente cliente) {
        //FIXME Verificar se o endereço do Cliente ja existe (pelo CEP).
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            //FIXME Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        //FIXME Insrir Cliente, vinculando o Endereço (novo ou existente).
        clienteRepository.save(cliente);
    }


}
