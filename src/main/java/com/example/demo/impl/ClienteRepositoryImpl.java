package com.example.demo.impl;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Cliente;
import com.example.demo.filter.ClienteFilter;
import com.example.demo.query.ClienteRepositoryQuery;

import jakarta.persistence.criteria.*;

@Repository
public class ClienteRepositoryImpl implements ClienteRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public Page<Cliente> filtrar(ClienteFilter filter, Pageable pageable) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
        Root<Cliente> root = criteria.from(Cliente.class);

        // ✅ Usa array de Predicates (corrigido)
        Predicate[] predicates = criarRestricoes(filter, builder, root);
        criteria.where(predicates);

        criteria.orderBy(builder.asc(root.get("nome"))); // ordenação por nome

        TypedQuery<Cliente> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        // Conta total de registros
        Long total = contar(filter);
        List<Cliente> resultados = query.getResultList();

        return new PageImpl<>(resultados, pageable, total);
    }

    // ✅ Mantém retorno como array (mais limpo pro Criteria API)
    private Predicate[] criarRestricoes(ClienteFilter filter, CriteriaBuilder builder, Root<Cliente> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getNome() != null && !filter.getNome().isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get("nome")), "%" + filter.getNome().toLowerCase() + "%"));
        }

        if (filter.getSobrenome() != null && !filter.getSobrenome().isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get("sobrenome")), "%" + filter.getSobrenome().toLowerCase() + "%"));
        }

        if (filter.getCpf() != null && !filter.getCpf().isEmpty()) {
            predicates.add(builder.equal(root.get("cpf"), filter.getCpf()));
        }

        if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get("email")), "%" + filter.getEmail().toLowerCase() + "%"));
        }

        if (filter.getTelefone() != null && !filter.getTelefone().isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get("telefone")), "%" + filter.getTelefone().toLowerCase() + "%"));
        }

        if (filter.getDataNascDe() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataNasc"), filter.getDataNascDe()));
        }

        if (filter.getDataNascAte() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataNasc"), filter.getDataNascAte()));
        }

        return predicates.toArray(new Predicate[0]);
    }

    private Long contar(ClienteFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Cliente> root = criteria.from(Cliente.class);

        Predicate[] predicates = criarRestricoes(filter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalPorPagina);
    }
}
