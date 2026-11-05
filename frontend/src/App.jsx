import { useEffect, useState } from 'react';
import './style.css';

const API = 'http://localhost:8080/api';

export default function App() {
  const [stroski, setStroski] = useState([]);
  const [kategorije, setKategorije] = useState([]);
  const [naziv, setNaziv] = useState('');
  const [znesek, setZnesek] = useState('');
  const [kategorija, setKategorija] = useState('');
  const [urejanje, setUrejanje] = useState(null);
  const [izbran, setIzbran] = useState(null);
  const [napaka, setNapaka] = useState('');

  async function osvezi() {
    try {
      const a = await fetch(API + '/expenses');
      const b = await fetch(API + '/categories');
      if (!a.ok || !b.ok) throw new Error('Streznik ne odgovarja');
      setStroski(await a.json());
      setKategorije(await b.json());
      setNapaka('');
    } catch { setNapaka('Preveri, ali je backend zagnan.'); }
  }

  useEffect(() => { osvezi(); }, []);

  async function shrani(e) {
    e.preventDefault();
    const podatki = { naziv, znesek: Number(znesek), kategorija: { id: Number(kategorija) } };
    const url = API + '/expenses' + (urejanje === null ? '' : '/' + urejanje);
    const odgovor = await fetch(url, {
      method: urejanje === null ? 'POST' : 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(podatki)
    });
    if (!odgovor.ok) { setNapaka('Shranjevanje ni uspelo.'); return; }
    setNaziv(''); setZnesek(''); setKategorija(''); setUrejanje(null);
    await osvezi();
  }

  function uredi(s) {
    setUrejanje(s.id);
    setNaziv(s.naziv);
    setZnesek(String(s.znesek));
    setKategorija(String(s.kategorija.id));
  }

  async function izbrisi(id) {
    const odgovor = await fetch(API + '/expenses/' + id, { method: 'DELETE' });
    if (!odgovor.ok) { setNapaka('Brisanje ni uspelo.'); return; }
    setIzbran(null);
    await osvezi();
  }

  async function podrobnosti(id) {
    const odgovor = await fetch(API + '/expenses/' + id);
    if (odgovor.ok) setIzbran(await odgovor.json());
  }

  return <main>
    <h1>Expense Tracker</h1>
    <p>Preprosta aplikacija za spremljanje stroskov</p>
    <h2>{urejanje === null ? 'Dodaj strosek' : 'Uredi strosek'}</h2>
    <form onSubmit={shrani}>
      <input required placeholder="Naziv" value={naziv} onChange={e => setNaziv(e.target.value)} />
      <input required type="number" min="0.01" step="0.01" placeholder="Znesek" value={znesek} onChange={e => setZnesek(e.target.value)} />
      <select required value={kategorija} onChange={e => setKategorija(e.target.value)}>
        <option value="">Izberi kategorijo</option>
        {kategorije.map(k => <option key={k.id} value={k.id}>{k.naziv}</option>)}
      </select>
      <button type="submit">{urejanje === null ? 'Dodaj' : 'Shrani spremembe'}</button>
      {urejanje !== null && <button type="button" onClick={() => { setUrejanje(null); setNaziv(''); setZnesek(''); setKategorija(''); }}>Preklici</button>}
    </form>

    <h2>Seznam stroskov</h2>
    <button onClick={osvezi}>Osvezi seznam</button>
    <table><thead><tr><th>ID</th><th>Naziv</th><th>Znesek</th><th>Kategorija</th><th>Dejanja</th></tr></thead>
      <tbody>{stroski.map(s => <tr key={s.id}><td>{s.id}</td><td>{s.naziv}</td><td>{s.znesek} EUR</td><td>{s.kategorija?.naziv}</td><td>
        <button onClick={() => podrobnosti(s.id)}>Poglej</button>
        <button onClick={() => uredi(s)}>Uredi</button>
        <button onClick={() => izbrisi(s.id)}>Izbrisi</button>
      </td></tr>)}</tbody></table>
    {izbran && <p>Izbran strosek: {izbran.naziv}, {izbran.znesek} EUR, {izbran.kategorija?.naziv}</p>}
    {napaka && <p className="napaka">{napaka}</p>}
  </main>;
}
