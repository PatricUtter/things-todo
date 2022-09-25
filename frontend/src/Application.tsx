import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import AboutPage from './pages/About';
import Homepage from './pages/Home';
import TodoPage from './pages/Todo';

export interface IApplicationProps {}

const Application: React.FunctionComponent<IApplicationProps> = (props) => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Homepage />} />
                <Route path="about" element={<AboutPage />} />
                <Route path="todo" element={<TodoPage />} />
            </Routes>
        </BrowserRouter>
    );
};

export default Application;
