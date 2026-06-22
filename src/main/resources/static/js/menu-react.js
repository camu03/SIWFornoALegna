(function () {
    var e = React.createElement;

    function MenuApp() {
        var menuState = React.useState(null);
        var menu = menuState[0]; var setMenu = menuState[1];

        var searchState = React.useState('');
        var ricerca = searchState[0]; var setRicerca = searchState[1];

        var selectedState = React.useState(null);
        var selectedItem = selectedState[0]; var setSelectedItem = selectedState[1];

        // Caricamento iniziale: prime 10 per categoria
        React.useEffect(function () {
            fetch('/api/menu')
                .then(function (r) { return r.json(); })
                .then(function (data) { setMenu(data); })
                .catch(function (err) { console.error("Errore nel caricamento:", err); });
        }, []);

        // Ricerca: chiamata al server ogni volta che cambia il testo
        React.useEffect(function () {
            if (ricerca === '') {
                // Torna alle prime 10
                fetch('/api/menu')
                    .then(function (r) { return r.json(); })
                    .then(function (data) { setMenu(data); })
                    .catch(function (err) { console.error("Errore:", err); });
            } else {
                fetch('/api/menu/search?q=' + encodeURIComponent(ricerca))
                    .then(function (r) { return r.json(); })
                    .then(function (data) { setMenu(data); })
                    .catch(function (err) { console.error("Errore ricerca:", err); });
            }
        }, [ricerca]);

        if (!menu) {
            return e('p', { style: { textAlign: 'center', color: '#666' } }, 'Caricamento menu...');
        }

        var totale = menu.pizze.length + menu.bibite.length + menu.fritti.length;

        function Card(item, tipo) {
            var imgUrl = item.hasImmagine ? ('/' + tipo + '/immagine/' + item.id) : null;
            var ingredienti = (item.ingredienti && item.ingredienti.length > 0)
                ? item.ingredienti.join(', ') : null;

            return e('div', {
                key: item.id,
                className: 'pizza-card',
                style: { cursor: 'pointer' },
                onClick: function () { setSelectedItem({ item: item, tipo: tipo }); }
            },
                imgUrl
                    ? e('img', {
                        src: imgUrl, alt: item.nome,
                        style: { width: '100%', height: '200px', objectFit: 'cover',
                                 borderRadius: '10px 10px 0 0', marginBottom: '15px' }
                    })
                    : null,
                e('h3', null, item.nome),
                ingredienti
                    ? e('p', { style: { color: '#666', fontSize: '0.85rem', margin: '6px 0' } }, ingredienti)
                    : null,
                e('span', { className: 'prezzo' }, '€ ' + item.prezzo.toFixed(2))
            );
        }

        function Sezione(titolo, lista, tipo) {
            if (lista.length === 0) return null;
            return e('div', null,
                e('section', { className: 'section' },
                    e('div', { className: 'container' },
                        e('h2', { className: 'section-title', style: { textAlign: 'center', marginBottom: '20px' } }, titolo),
                        e('div', { className: 'pizza-grid' },
                            lista.map(function (item) { return Card(item, tipo); })
                        )
                    )
                ),
                e('hr', { style: { border: 0, height: '2px', background: '#c41e3a',
                                   margin: '50px auto', width: '80%', maxWidth: '1200px' } })
            );
        }

        // Modal
        var modal = null;
        if (selectedItem) {
            var si = selectedItem.item;
            var imgUrl = si.hasImmagine ? ('/' + selectedItem.tipo + '/immagine/' + si.id) : null;
            var ing = (si.ingredienti && si.ingredienti.length > 0) ? si.ingredienti.join(', ') : null;

            modal = e('div', {
                style: { display: 'flex', position: 'fixed', top: 0, left: 0, width: '100%', height: '100%',
                         background: 'rgba(0,0,0,0.5)', zIndex: 1000,
                         alignItems: 'center', justifyContent: 'center' },
                onClick: function (ev) { if (ev.target === ev.currentTarget) setSelectedItem(null); }
            },
                e('div', { style: { position: 'relative', background: 'white', padding: '2rem',
                                    borderRadius: '10px', width: '90%', maxWidth: '500px',
                                    boxShadow: '0 4px 20px rgba(0,0,0,0.3)', maxHeight: '90vh', overflowY: 'auto' } },
                    e('button', {
                        onClick: function () { setSelectedItem(null); },
                        style: { position: 'absolute', top: '1rem', right: '1rem', background: 'none',
                                 border: 'none', fontSize: '1.5rem', cursor: 'pointer', color: '#999' }
                    }, '×'),
                    imgUrl
                        ? e('img', { src: imgUrl, alt: si.nome,
                              style: { width: '100%', height: '200px', objectFit: 'cover',
                                       borderRadius: '8px', marginBottom: '1rem' } })
                        : null,
                    e('h2', { style: { margin: '0 0 0.5rem 0', color: '#c41e3a', fontSize: '1.5rem' } }, si.nome),
                    ing
                        ? e('p', { style: { margin: '0.4rem 0 0.8rem 0', color: '#888',
                                            fontSize: '0.85rem', fontStyle: 'italic' } }, ing)
                        : null,
                    e('p', { style: { margin: 0, color: '#666', lineHeight: 1.6 } }, si.descrizione),
                    e('button', {
                        onClick: function () { setSelectedItem(null); },
                        className: 'btn-primary',
                        style: { marginTop: '1.5rem', width: '100%', padding: '0.75rem',
                                 border: 'none', cursor: 'pointer', fontSize: '1rem', fontWeight: 600 }
                    }, 'Chiudi')
                )
            );
        }

        return e('div', null,
            // Barra di ricerca
            e('section', { className: 'section', style: { paddingBottom: '10px' } },
                e('div', { className: 'container', style: { textAlign: 'center' } },
                    e('input', {
                        type: 'text',
                        placeholder: 'Cerca nel menu...',
                        value: ricerca,
                        onChange: function (ev) { setRicerca(ev.target.value); },
                        style: { width: '60%', padding: '12px 20px', border: '2px solid #c41e3a',
                                 borderRadius: '25px', fontSize: '16px' }
                    }),
                    ricerca && totale === 0
                        ? e('p', { style: { marginTop: '20px', color: '#666' } },
                            'Nessun risultato per "' + ricerca + '"')
                        : null
                )
            ),
            Sezione('PIZZE', menu.pizze, 'pizze'),
            Sezione('BIBITE', menu.bibite, 'bibite'),
            Sezione('FRITTI', menu.fritti, 'fritti'),
            modal
        );
    }

    var rootEl = document.getElementById('menu-react-root');
    if (rootEl) {
        ReactDOM.createRoot(rootEl).render(e(MenuApp));
    }
})();
