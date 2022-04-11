import React from 'react';
import { createRoot } from 'react-dom/client'
import './index.css';
import reportWebVitals from './reportWebVitals';
import {GameTable} from 'compoments/GameTable'

const container = document.getElementById("root") as HTMLDivElement;
const root = createRoot(container);

root.render(<GameTable/>);

reportWebVitals();
