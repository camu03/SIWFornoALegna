(function () {
  function getNumber(value) {
    var parsed = Number.parseInt(value || "0", 10);
    return Number.isNaN(parsed) ? 0 : parsed;
  }

  function renderDashboard() {
    var rootElement = document.getElementById("admin-dashboard-root");
    if (!rootElement || !window.React || !window.ReactDOM) {
      return;
    }

    var pizzeCount = getNumber(rootElement.dataset.pizzeCount);
    var bibiteCount = getNumber(rootElement.dataset.bibiteCount);
    var frittiCount = getNumber(rootElement.dataset.frittiCount);
    var ingredientiCount = getNumber(rootElement.dataset.ingredientiCount);

    var e = React.createElement;

    function StatCard(props) {
      return e(
        "article",
        {
          style: {
            background: "#ffffff",
            borderRadius: "16px",
            boxShadow: "0 10px 30px rgba(0, 0, 0, 0.08)",
            padding: "1.5rem",
            border: "1px solid rgba(196, 30, 58, 0.08)",
            display: "flex",
            flexDirection: "column",
            gap: "0.75rem",
            minHeight: "220px",
          },
        },
        e(
          "div",
          {
            style: {
              width: "52px",
              height: "52px",
              borderRadius: "14px",
              display: "grid",
              placeItems: "center",
              background: props.accent,
              color: "#ffffff",
              fontSize: "1.2rem",
              fontWeight: "700",
            },
          },
          props.icon
        ),
        e(
          "h3",
          {
            style: {
              margin: 0,
              color: "#c41e3a",
              fontSize: "1.4rem",
            },
          },
          props.title
        ),
        e(
          "p",
          {
            style: {
              margin: 0,
              color: "#666",
              lineHeight: 1.6,
              flexGrow: 1,
            },
          },
          props.description
        ),
        e(
          "div",
          {
            style: {
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
              gap: "1rem",
              marginTop: "0.25rem",
            },
          },
          e(
            "strong",
            {
              style: {
                fontSize: "2rem",
                color: "#222",
              },
            },
            props.count
          ),
          e(
            "a",
            {
              href: props.href,
              className: "btn-primary",
              style: {
                textDecoration: "none",
                whiteSpace: "nowrap",
              },
            },
            "Vai alla gestione"
          )
        )
      );
    }

    function App() {
      var cards = [
        {
          key: "pizze",
          title: "Gestione Pizze",
          description: "Aggiungi, modifica o elimina pizze dal menu.",
          count: pizzeCount,
          href: "/admin/pizze",
          accent: "linear-gradient(135deg, #c41e3a, #ff6b6b)",
          icon: "P",
        },
        {
          key: "bibite",
          title: "Gestione Bibite",
          description: "Aggiorna le bibite disponibili e le relative immagini.",
          count: bibiteCount,
          href: "/admin/bibite",
          accent: "linear-gradient(135deg, #2f80ed, #56ccf2)",
          icon: "B",
        },
        {
          key: "fritti",
          title: "Gestione Fritti",
          description: "Organizza i fritti e mantieni il catalogo aggiornato.",
          count: frittiCount,
          href: "/admin/fritti",
          accent: "linear-gradient(135deg, #f2994a, #f2c94c)",
          icon: "F",
        },
        {
          key: "ingredienti",
          title: "Gestione Ingredienti",
          description: "Aggiungi o rimuovi ingredienti da associare alle pizze.",
          count: ingredientiCount,
          href: "/admin/ingredienti",
          accent: "linear-gradient(135deg, #27ae60, #6fcf97)",
          icon: "I",
        },
      ];

      return e(
        "div",
        {
          style: {
            display: "grid",
            gap: "1.5rem",
          },
        },
        e(
          "div",
          {
            style: {
              background: "linear-gradient(135deg, #fff7f8, #ffffff)",
              borderRadius: "18px",
              padding: "1.5rem",
              border: "1px solid rgba(196, 30, 58, 0.1)",
            },
          },
          e(
            "p",
            {
              style: {
                margin: 0,
                color: "#c41e3a",
                fontWeight: 700,
                letterSpacing: "0.04em",
                textTransform: "uppercase",
                fontSize: "0.85rem",
              },
            },
            "Dashboard React"
          ),
          e(
            "h3",
            {
              style: {
                margin: "0.5rem 0 0.5rem 0",
                fontSize: "1.8rem",
                color: "#222",
              },
            },
            "Gestione Menu Pizzeria"
          ),
          e(
            "p",
            {
              style: {
                margin: 0,
                color: "#666",
                lineHeight: 1.6,
                maxWidth: "720px",
              },
            },
          )
        ),
        e(
          "div",
          {
            style: {
              display: "grid",
              gridTemplateColumns: "repeat(auto-fit, minmax(260px, 1fr))",
              gap: "1.25rem",
            },
          },
          cards.map(function (card) {
            return e(StatCard, {
              key: card.key,
              title: card.title,
              description: card.description,
              count: card.count,
              href: card.href,
              accent: card.accent,
              icon: card.icon,
            });
          })
        )
      );
    }

    ReactDOM.createRoot(rootElement).render(e(App));
  }

  if (document.readyState === "loading") {
    document.addEventListener("DOMContentLoaded", renderDashboard);
  } else {
    renderDashboard();
  }
})();
