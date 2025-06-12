import { useState } from 'react';

export function ExpenseForm({ onAdd }) {
  const [name, setName] = useState('');
  const [amount, setAmount] = useState('');

  function handleSubmit(e) {
    e.preventDefault();
    if (!name || !amount) return;
    onAdd({ name, amount: parseFloat(amount) });
    setName('');
    setAmount('');
  }

  return (
    <form onSubmit={handleSubmit} style={{ marginBottom: 16 }}>
      <h2>Add Expense</h2>
      <input
        type="text"
        placeholder="Expense name"
        value={name}
        onChange={e => setName(e.target.value)}
        required
      />
      <input
        type="number"
        placeholder="Amount"
        value={amount}
        onChange={e => setAmount(e.target.value)}
        required
        min="0"
        step="0.01"
      />
      <button type="submit">Add</button>
    </form>
  );
}

export function BudgetForm({ onAdd }) {
  const [name, setName] = useState('');
  const [limit, setLimit] = useState('');

  function handleSubmit(e) {
    e.preventDefault();
    if (!name || !limit) return;
    onAdd({ name, limit: parseFloat(limit) });
    setName('');
    setLimit('');
  }

  return (
    <form onSubmit={handleSubmit} style={{ marginBottom: 16 }}>
      <h2>Add Budget</h2>
      <input
        type="text"
        placeholder="Budget name"
        value={name}
        onChange={e => setName(e.target.value)}
        required
      />
      <input
        type="number"
        placeholder="Limit"
        value={limit}
        onChange={e => setLimit(e.target.value)}
        required
        min="0"
        step="0.01"
      />
      <button type="submit">Add</button>
    </form>
  );
}

export function ExpenseList({ expenses }) {
  return (
    <div>
      <h2>Expenses</h2>
      <ul>
        {expenses.length === 0 && <li>No expenses yet.</li>}
        {expenses.map((e, i) => (
          <li key={i}>{e.name}: ${e.amount.toFixed(2)}</li>
        ))}
      </ul>
    </div>
  );
}

export function BudgetList({ budgets }) {
  return (
    <div>
      <h2>Budgets</h2>
      <ul>
        {budgets.length === 0 && <li>No budgets yet.</li>}
        {budgets.map((b, i) => (
          <li key={i}>{b.name}: ${b.limit.toFixed(2)}</li>
        ))}
      </ul>
    </div>
  );
}
