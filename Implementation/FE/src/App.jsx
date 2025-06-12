import { useState } from 'react';
import './App.css';

const SIDEBAR_LINKS = [
  { label: 'Home', icon: <svg width="20" height="20" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24"><path d="M3 12L12 3l9 9"/><path d="M9 21V9h6v12"/></svg> },
  { label: 'Expenses', icon: <svg width="20" height="20" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24"><rect x="3" y="11" width="18" height="10" rx="2"/><path d="M8 11V7a4 4 0 1 1 8 0v4"/></svg> },
  { label: 'Budgets', icon: <svg width="20" height="20" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24"><circle cx="12" cy="12" r="10"/><path d="M12 6v6l4 2"/></svg> },
];

function Sidebar({ current, setCurrent }) {
  return (
    <aside className="sidebar">
      <h2 className="sidebar-title">SpendSense</h2>
      <nav>
        <ul>
          {SIDEBAR_LINKS.map(link => (
            <li key={link.label}>
              <button
                className={current === link.label ? 'active' : ''}
                onClick={() => setCurrent(link.label)}
                aria-label={link.label}
              >
                <span className="icon">{link.icon}</span> {link.label}
              </button>
            </li>
          ))}
        </ul>
      </nav>
    </aside>
  );
}

function ExpenseForm({ onAdd }) {
  const [category, setCategory] = useState('General');
  const [description, setDescription] = useState('');
  const [amount, setAmount] = useState('');

  function handleSubmit(e) {
    e.preventDefault();
    if (!description || !amount) return;
    onAdd({ category, description, amount: parseFloat(amount) });
    setDescription('');
    setAmount('');
  }

  return (
    <form className="expense-form-horizontal" onSubmit={handleSubmit}>
      <h3>Expense</h3>
      <div className="expense-input-row">
        <div className="expense-input-group">
          <label>Category</label>
          <select value={category} onChange={e => setCategory(e.target.value)}>
            <option>General</option>
            <option>Food</option>
            <option>Transport</option>
            <option>Shopping</option>
            <option>Utilities</option>
            <option>Other</option>
          </select>
        </div>
        <div className="expense-input-group">
          <label>Description</label>
          <input
            type="text"
            value={description}
            onChange={e => setDescription(e.target.value)}
            placeholder="e.g. Coffee at Starbucks"
            required
          />
        </div>
        <div className="expense-input-group">
          <label>Amount</label>
          <input
            type="number"
            value={amount}
            onChange={e => setAmount(e.target.value)}
            placeholder="$0.00"
            min="0"
            step="0.01"
            required
          />
        </div>
      </div>
      <button className="expense-form-btn" type="submit">Add New Expense</button>
    </form>
  );
}

function ExpenseCard({ expense }) {
  return (
    <div className="expense-card">
      <div className="expense-category">{expense.category}</div>
      <div className="expense-desc">{expense.description}</div>
      <div className="expense-amount">${expense.amount.toFixed(2)}</div>
    </div>
  );
}

function ExpensesSection({ expenses, onAdd }) {
  return (
    <section className="expenses-section expenses-section-grid">
      <div className="expense-form-wrapper">
        <ExpenseForm onAdd={onAdd} />
      </div>
      <div className="expense-list-wrapper">
        <h3>My Expenses</h3>
        <div className="expense-list-grid">
          {expenses.length === 0 && [1,2,3].map(i => (
            <div className="expense-card-grid" key={i}>
              <div className="expense-card-title">Expense {i}</div>
              <div className="expense-card-placeholder">...</div>
            </div>
          ))}
          {expenses.map((e, i) => (
            <div className="expense-card-grid" key={i}>
              <div className="expense-card-title">{e.category}</div>
              <div className="expense-card-desc">{e.description}</div>
              <div className="expense-card-amount">${e.amount.toFixed(2)}</div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}

function HomeSection() {
  return (
    <section className="home-section">
      <h2>Welcome to SpendSense</h2>
      <p>Track your expenses and budgets with ease.</p>
    </section>
  );
}

function BudgetsSection() {
  return (
    <section className="budgets-section">
      <h2>Budgets</h2>
      <p>Budget management coming soon!</p>
    </section>
  );
}

function App() {
  const [current, setCurrent] = useState('Home');
  const [expenses, setExpenses] = useState([]);

  return (
    <div className="app-layout">
      <Sidebar current={current} setCurrent={setCurrent} />
      <main className="main-content">
        {current === 'Home' && <HomeSection />}
        {current === 'Expenses' && <ExpensesSection expenses={expenses} onAdd={e => setExpenses(prev => [...prev, e])} />}
        {current === 'Budgets' && <BudgetsSection />}
      </main>
    </div>
  );
}

export default App;
